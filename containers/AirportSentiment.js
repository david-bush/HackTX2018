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

export default class AirportSentiment extends Component {

  constructor(){
      super();
      this.state = {
          sentiments: [],
          textFieldValue:"",
          isLoaded:false
      }
      this.fetchAirportSentiment = this.fetchAirportSentiment.bind(this);
      this.handleInputChange = this.handleInputChange.bind(this);

  }

  componentDidMount() {
    axios.get('http://10.147.163.107:5000/api/airport_data')
        .then(response => {
              //console.log(response.json);
              console.log(response.data.result);
              this.setState({sentiments: response.data.result, isLoaded:true});
              console.log(this.state.sentiments);
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
    console.log(this.state.sentiments);
    // return (
    //   <p>{this.state.sentiments[0].score}</p>
    // );
    //console.log(this.state.sentiments);
    // var json=JSON.parse(this.state.sentiments[0])
    // console.log(json);
    // console.log(json['category']);
    // console.log(this.state.sentiments[0]);
    // console.log(this.state.sentiments[0].category);
    var arr = [];
    if (this.state.isLoaded){
    for (var i = 0; i < 20; i++) {
      //if (this.state.sentiments[i].category==='airport'){
        arr.push({
          airportCode: this.state.sentiments[i].airportCode,
          score: this.state.sentiments[i].score
        });
      //}
    }
    //console.log(arr);
    }
    return (
      <div>
        <div style={{margin:30}}>
            <Input placeholder="Enter in an airport" onChange={this.handleInputChange}/>
            <Button variant="outlined" color="primary" onClick={this.fetchAirportSentiment}>Search</Button>
        </div>
        <div>
          <BarChart width={1500} height={500} data={arr}
            margin={{top: 5, right: 30, left: 50, bottom: 5}}>
            <CartesianGrid strokeDasharray="3 3"/>
            <XAxis dataKey="airportCode"/>
            <YAxis/>
            <Tooltip/>
            <Legend />
            <Bar dataKey="score" fill="#8884d8" />
          </BarChart>
        </div>
      </div>


    );
  }
}
