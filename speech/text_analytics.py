########### Python 2.7 #############
import requests
from pprint import pprint
import json

subscription_key = "c5edd6e7ed6949a39748ff3a56543ea9"
assert subscription_key

text_analytics_base_url = "https://westus.api.cognitive.microsoft.com/text/analytics/v2.0"

def get_sentiments(documents):
    sentiment_api_url = text_analytics_base_url + "/sentiment"
    print(sentiment_api_url)

    headers   = {"Ocp-Apim-Subscription-Key": subscription_key}
    response  = requests.post(sentiment_api_url, headers=headers, json=documents)
    sentiments = response.json()
    return sentiments


def get_key_phrases(documents):
    key_phrase_api_url = text_analytics_base_url + "/keyPhrases"
    print(key_phrase_api_url)

    headers   = {'Ocp-Apim-Subscription-Key': subscription_key}
    response  = requests.post(key_phrase_api_url, headers=headers, json=documents)
    key_phrases = response.json()
    return key_phrases


def get_entities(documents):
    entity_linking_api_url = text_analytics_base_url + "/entities"
    print(entity_linking_api_url)
    headers   = {"Ocp-Apim-Subscription-Key": subscription_key}
    response  = requests.post(entity_linking_api_url, headers=headers, json=documents)
    entities = response.json()
    return entities


def parse_text(src_path):
    documents = {}
    results = []

    with open(src_path, "r") as file: 
        for i, value in enumerate(file):
            temp = {}
            temp['id'] = str(i + 1)
            temp['language'] = 'en'
            temp['text'] = value
            results.append(temp)
        
        documents['documents'] = results
        
    return documents


def extract(doc, category):
    sentiments = get_sentiments(doc)['documents']
    entities = get_entities(doc)['documents']
    key_phrases = get_key_phrases(doc)['documents']

    results = []

    for i in range(0, len(sentiments), 1):
        sentiments[i].update(entities[i])
        sentiments[i].update(key_phrases[i])

        original_text = doc['documents'][i]['text']
        sentiments[i]['original_text'] = original_text

        results.append(sentiments[i])

    result_dict = {}
    result_dict['result'] = results
    result_dict['category'] = category
    results_json = json.dumps(result_dict)
    return results_json


def send_data_to_david(api_url, result):
    headers = {'Content-type': 'application/json'}
    response = requests.post(api_url, headers=headers, data=result)
    print (response)
    # print(response.status)
            
if __name__ == "__main__":
    documents = {'documents' : [
    {'id': '1', 'language': 'en', 'text': 'I had a wonderful experience! The rooms were wonderful and the staff was helpful.'},
    {'id': '2', 'language': 'en', 'text': 'I had a terrible time at the hotel. The staff was rude and the food was awful.'},  
    ]}

    airport = parse_text("AirportLines.txt")
    flight = parse_text("FlightLines.txt")

    airport_result = extract(airport, "airport")
    flight_result = extract(flight, "flight")

    # temp_result = extract(documents, "temp")

    send_data_to_david("http://127.0.0.1:5000/api/data", airport_result)
    send_data_to_david("http://127.0.0.1:5000/api/data", flight_result)