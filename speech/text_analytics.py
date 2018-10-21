########### Python 2.7 #############
import requests
from pprint import pprint

subscription_key = "c5edd6e7ed6949a39748ff3a56543ea9"
assert subscription_key

text_analytics_base_url = "https://westus.api.cognitive.microsoft.com/text/analytics/v2.0"

key_phrase_api_url = text_analytics_base_url + "keyPhrases"
print(key_phrase_api_url)

# def get_sentiments(text-analytics_base_url, subscription_key, ):
sentiment_api_url = text_analytics_base_url + "/sentiment"
print(sentiment_api_url)

documents = {'documents' : [
{'id': '1', 'language': 'en', 'text': 'I had a wonderful experience! The rooms were wonderful and the staff was helpful.'},
{'id': '2', 'language': 'en', 'text': 'I had a terrible time at the hotel. The staff was rude and the food was awful.'}
]}

headers   = {"Ocp-Apim-Subscription-Key": subscription_key}
response  = requests.post(sentiment_api_url, headers=headers, json=documents)
sentiments = response.json()
pprint(sentiments)
