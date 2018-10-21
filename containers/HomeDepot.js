import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import Button from '@material-ui/core/Button';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Tabs from '@material-ui/core/Tabs';
import Input from '@material-ui/core/Input';
import Tab from '@material-ui/core/Tab';
import PropTypes from 'prop-types';
import axios from 'axios';
import { BarChart,CartesianGrid,XAxis,YAxis,Tooltip,Legend,Bar} from 'recharts';
const data1=[{"category": "airport", "airportCode": "AAX", "_id": {"$oid": "5bcc556b7823853db9c2e587"}, "score": "0.0401107370853", "listPhrases": "[u'flight delay', u\"daughter's wedding\"]"}, {"category": "airport", "airportCode": "ADJ", "_id": {"$oid": "5bcc556b7823853db9c2e588"}, "score": "0.186949044466", "listPhrases": "[u'check']"}, {"category": "airport", "airportCode": "AJR", "_id": {"$oid": "5bcc556b7823853db9c2e589"}, "score": "0.728898644447", "listPhrases": "[u'baggage', u'huge hassle']"}, {"category": "airport", "airportCode": "AFT", "_id": {"$oid": "5bcc556b7823853db9c2e58a"}, "score": "0.5", "listPhrases": "[u'hours', u'airport', u'flight']"}, {"category": "airport", "airportCode": "ABR", "_id": {"$oid": "5bcc556b7823853db9c2e58b"}, "score": "0.183482795954", "listPhrases": "[u'food options', u'airport']"}, {"category": "airport", "airportCode": "ABT", "_id": {"$oid": "5bcc556b7823853db9c2e58c"}, "score": "0.5", "listPhrases": "[u'hours', u'security line']"}, {"category": "airport", "airportCode": "AAL", "_id": {"$oid": "5bcc556b7823853db9c2e58d"}, "score": "0.723627150059", "listPhrases": "[u'security', u'time']"}, {"category": "airport", "airportCode": "AJI", "_id": {"$oid": "5bcc556b7823853db9c2e58e"}, "score": "0.0579520761967", "listPhrases": "[u'staff', u'check']"}, {"category": "airport", "airportCode": "AKK", "_id": {"$oid": "5bcc556b7823853db9c2e58f"}, "score": "0.193485021591", "listPhrases": "[u'flight', u'time']"}, {"category": "airport", "airportCode": "ADZ", "_id": {"$oid": "5bcc556b7823853db9c2e590"}, "score": "0.750684797764", "listPhrases": "[u'boarding process', u'staff']"}, {"category": "airport", "airportCode": "ACV", "_id": {"$oid": "5bcc556b7823853db9c2e591"}, "score": "0.5", "listPhrases": "[u'airport']"}, {"category": "airport", "airportCode": "ADE", "_id": {"$oid": "5bcc556b7823853db9c2e592"}, "score": "0.5", "listPhrases": "[u'staff memember', u'line']"}, {"category": "airport", "airportCode": "AIY", "_id": {"$oid": "5bcc556b7823853db9c2e593"}, "score": "0.904908180237", "listPhrases": "[u'service', u'help desk']"}, {"category": "airport", "airportCode": "AGH", "_id": {"$oid": "5bcc556b7823853db9c2e594"}, "score": "0.858547687531", "listPhrases": "[u'problems', u'security']"}, {"category": "airport", "airportCode": "AJP", "_id": {"$oid": "5bcc556b7823853db9c2e595"}, "score": "0.251718997955", "listPhrases": "[u'hold', u'flight']"}, {"category": "airport", "airportCode": "AIT", "_id": {"$oid": "5bcc556b7823853db9c2e596"}, "score": "0.827305078506", "listPhrases": "[u'customer service']"}, {"category": "airport", "airportCode": "AGF", "_id": {"$oid": "5bcc556b7823853db9c2e597"}, "score": "0.220921367407", "listPhrases": "[u'line']"}, {"category": "airport", "airportCode": "AFL", "_id": {"$oid": "5bcc556b7823853db9c2e598"}, "score": "0.904475688934", "listPhrases": "[u'help desk', u'service']"}, {"category": "airport", "airportCode": "ABY", "_id": {"$oid": "5bcc556b7823853db9c2e599"}, "score": "0.241851985455", "listPhrases": "[u'people', u'TSA']"}, {"category": "airport", "airportCode": "AGV", "_id": {"$oid": "5bcc556b7823853db9c2e59a"}, "score": "0.0455753803253", "listPhrases": "[u'time', u'bad weather']"}, {"category": "airport", "airportCode": "AAQ", "_id": {"$oid": "5bcc556b7823853db9c2e59b"}, "score": "0.153041273355", "listPhrases": "[u'experience']"}];

export default class AirportSentiment extends Component {

  constructor(){
      super();
      this.state = {
          text: {},
          textFieldValue:"",
          isLoaded:false
      }
      this.fetchAirportSentiment = this.fetchAirportSentiment.bind(this);
      this.handleInputChange = this.handleInputChange.bind(this);

  }

  componentDidMount() {
    axios.get('http://10.147.163.107:5000/api/new_data')
        .then(response => {
              //console.log(response.json);
              console.log(response.data.result);
              this.setState({text: response.data.result, isLoaded:true});
              console.log(this.state.text);
//                 this.setState( (state) => {
//     state.sentiments = state.sentiments.concat(response.data);
//     return state;
// });
        }),
	       (error) => { console.log(error) };

  }

  // componentDidMount() {
  //   // http://10.147.163.107:5000/api/airport_data
  //   // https://api.example.com/items
  //     axios.get('http://10.147.163.107:5000/api/airport_data')
  //       .then(res => console.log(res.json()))
  //       .then(
  //         (result) => {
  //           this.setState({
  //             sentiments: result.data.result
  //           });
  //           console.log(result);
  //         },
  //         // Note: it's important to handle errors here
  //         // instead of a catch() block so that we don't swallow
  //         // exceptions from actual bugs in components.
  //
  //
  //       )
  //       console.log(this.state.sentiments);
  //   }

    fetchAirportSentiment(){
//       // http://awesomeserver/users.username
//       axios.get('http://10.147.163.107:5000/api/airport_data')
//           .then(response => {
//                 console.log(response.data.result);
//                 this.setState({sentiments: response.data.result});
//                 console.log(this.state.sentiments);
// //                 this.setState( (state) => {
// //     state.sentiments = state.sentiments.concat(response.data);
// //     return state;
// // });
//            })
    }

    handleInputChange(){
        this.setState({
            textFieldValue: e.target.value
        });
    }

  render () {
    console.log(this.state.text);
    var arr = [];
    if (this.state.isLoaded){

    //console.log(arr);
    }
    return (
      <div>

      </div>


    );
  }
}
