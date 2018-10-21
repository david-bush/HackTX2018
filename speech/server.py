#!flask/bin/python
from flask import Flask, jsonify
from flask import request
from flask import abort 
from bson.json_util import dumps
import pymongo
import json


myclient = pymongo.MongoClient("mongodb://localhost:27017/")

mydb = myclient["aa-mock-engine"]

app = Flask(__name__)

tasks = [
    {
        'id': 1,
        'title': u'Buy groceries',
        'description': u'Milk, Cheese, Pizza, Fruit, Tylenol', 
        'done': False
    },
    {
        'id': 2,
        'title': u'Learn Python',
        'description': u'Need to find a good Python tutorial on the web', 
        'done': False
    }
]

# @app.route('/data', methods=['POST'])
# def get_tasks():
#     return jsonify({'tasks': tasks})

# gets flight data from mongodb and returns json
@app.route('/api/flight_data', methods=["GET"])
def get_flight_data():
    return dumps(mydb.sentiment.find({"category" : "flight"}))

# gets airport data from mongodb and returns json
@app.route('/api/airport_data', methods=["GET"])
def get_airport_data():
    return dumps(mydb.sentiment.find({"category" : "airport"}))

@app.route('/api/data', methods=['POST'])
def create_task():
    if not request.json:
        abort(400)
    print (request.json)
    task = {
        # 'id': tasks[-1]['id'] + 1,
        # 'title': request.json['title'],
        # 'description': request.json.get('description', ""),
        # 'done': False
    }

    # print (type(request.json))
    # parsed_json = open(request.json, "r").read()
    print(request.json['result'])

    resultr_arr = request.json['result']
    category = request.json['category']

    for result in resultr_arr:
        record = {
            "score" : str(result["score"]),
            "listPhrases" : str(result["keyPhrases"]),
            "category" : category
        }
        result=mydb.sentiment.insert(record)


    # do some shit with mongodb


    return jsonify({'task': task}), 201

if __name__ == '__main__':
    app.run(debug=True)