import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import Button from '@material-ui/core/Button';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import PropTypes from 'prop-types';
import axios from 'axios';
import Input from '@material-ui/core/Input';
import { BarChart,CartesianGrid,XAxis,YAxis,Tooltip,Legend,Bar} from 'recharts';


const data1=[
    {
      "category": "flight",
      "flightNumber": 2442,
      "listPhrases": "[u'flight attendants', u'kind']",
      "score": "0.83468657732"
    },
    {
      "category": "flight",
      "flightNumber": 2426,
      "listPhrases": "[u'flight attendant', u'drink']",
      "score": "0.229708462954"
    },
    {
      "category": "flight",
      "flightNumber": 2477,
      "listPhrases": "[u'good time', u'flight']",
      "score": "0.828310549259"
    },
    {
      "category": "flight",
      "flightNumber": 2495,
      "listPhrases": "[u'seat', u'plane']",
      "score": "0.955289363861"
    },
    {
      "category": "flight",
      "flightNumber": 2425,
      "listPhrases": "[u'flight']",
      "score": "0.865471839905"
    },
    {
      "category": "flight",
      "flightNumber": 2419,
      "listPhrases": "[u'flight attendants', u'food']",
      "score": "0.0538561046124"
    },
    {
      "category": "flight",
      "flightNumber": 447,
      "listPhrases": "[u'flight', u'time']",
      "score": "0.246974289417"
    },
    {
      "category": "flight",
      "flightNumber": 2103,
      "listPhrases": "[u'service', u'flight']",
      "score": "0.812149822712"
    },
    {
      "category": "flight",
      "flightNumber": 6041,
      "listPhrases": "[u'service', u'flight']",
      "score": "0.878552317619"
    },
    {
      "category": "flight",
      "flightNumber": 761,
      "listPhrases": "[u'people', u'flight']",
      "score": "0.18945389986"
    },
    {
      "category": "flight",
      "flightNumber": 2460,
      "listPhrases": "[u'seat', u'plan']",
      "score": "0.0544140040874"
    },
    {
      "category": "flight",
      "flightNumber": 2479,
      "listPhrases": "[u'flight attendants']",
      "score": "0.229652315378"
    },
    {
      "category": "flight",
      "flightNumber": 1627,
      "listPhrases": "[u'good job', u'flight enjoyable', u'pilot']",
      "score": "0.933033287525"
    },
    {
      "category": "flight",
      "flightNumber": 2510,
      "listPhrases": "[u'rest', u'plane']",
      "score": "0.892666339874"
    },
    {
      "category": "flight",
      "flightNumber": 152,
      "listPhrases": "[u'American Airlines']",
      "score": "0.948495864868"
    },
    {
      "category": "flight",
      "flightNumber": 2424,
      "listPhrases": "[u'flight']",
      "score": "0.00569546222687"
    },
    {
      "category": "flight",
      "flightNumber": 2463,
      "listPhrases": "[u'flight attendants', u'experience']",
      "score": "0.779909133911"
    },
    {
      "category": "flight",
      "flightNumber": 2424,
      "listPhrases": "[u'content', u'experience']",
      "score": "0.90461564064"
    },
    {
      "category": "flight",
      "flightNumber": 1246,
      "listPhrases": "[u'flight attendants']",
      "score": "0.174895524979"
    },
    {
      "category": "flight",
      "flightNumber": 1159,
      "listPhrases": "[u'flight']",
      "score": "0.842966794968"
    }
  ];
export default class FlightSentiment extends Component {
  constructor(){
      super();
      this.state = {
          sentiments: [],
          textFieldValue:""

      }
      this.fetchFlightSentiment = this.fetchFlightSentiment.bind(this);
      this.handleInputChange = this.handleInputChange.bind(this);

  }
  // componentDidMount() {
  //   axios.get('http://10.147.163.107:5000/api/flight_data')
  //       .then(response => {
  //             this.setState({sentiments: response.result})
  //        })
  // }
    /*componentDidMount() {
        fetch("https://api.example.com/items")
          .then(res => res.json())
          .then(
            (result) => {
              this.setState({
                isLoaded: true,
                items: result.items
              });
            },
            // Note: it's important to handle errors here
            // instead of a catch() block so that we don't swallow
            // exceptions from actual bugs in components.
            (error) => {
              this.setState({
                isLoaded: true,
                error
              });
            }
          )
      }*/
    fetchFlightSentiment(){
      // axios.get('http://awesomeserver/users.username')
      //     .then(res => {
      //          return res.json()
      //     }).then(response => {
      //           this.setState({sentiments: response.sentiments})
      //      })
    }
    handleInputChange(){
        this.setState({
            textFieldValue: e.target.value
        });
    }

  render () {
    var arr = [];
    for (var i = 0; i < 20; i++) {
      //if (data1[i].category==='flight'){
        arr.push({
          flightCode: data1[i].flightNumber,
          score: data1[i].score
        });
      //}
    }
    return (
      <div style={{margin:30}}>
          <Input placeholder="Enter in a flight" onChange={this.handleInputChange} />
          <Button variant="outlined" color="primary" onClick={this.fetchFlightSentiment}>Search</Button>
          <BarChart width={1500} height={500} data={arr}
            margin={{top: 5, right: 30, left: 40, bottom: 5}}>
          <CartesianGrid strokeDasharray="3 3"/>
          <XAxis dataKey="flightNumber"/>
          <YAxis/>
          <Tooltip/>
          <Legend />
          <Bar dataKey="score" fill="#8884d8" />
          </BarChart>
      </div>
    );
  }
}
