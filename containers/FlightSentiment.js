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



export default class FlightSentiment extends Component {
  constructor(){
      super();
      this.state = {
          sentiments: [],
          textFieldValue:"",
          isLoaded:false

      }
      this.fetchFlightSentiment = this.fetchFlightSentiment.bind(this);
      this.handleInputChange = this.handleInputChange.bind(this);

  }
  componentDidMount() {
    axios.get('http://10.147.163.107:5000/api/flight_data')
        .then(response => {
              console.log(response.data.result);
              this.setState({sentiments: response.data.result, isLoaded:true});
              console.log(this.state.sentiments);

        }),
	       (error) => { console.log(error) };

  }
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
    console.log(this.state.sentiments);

    var arr = [];
    if (this.state.isLoaded){
      for (var i = 0; i < 20; i++) {
      if (this.state.sentiments[i].category==='flight'){
        arr.push({
          flightNumber: this.state.sentiments[i].flightNumber,
          score: this.state.sentiments[i].score
        });
      }
      }
    }
    return (
      <div>
        <div style={{margin:30}}>
          <Input placeholder="Enter in a flight" onChange={this.handleInputChange} />
          <Button variant="outlined" color="primary" onClick={this.fetchFlightSentiment}>Search</Button>
        </div>
        <div>
          <BarChart width={1500} height={500} data={arr}
            margin={{top: 5, right: 30, left: 50, bottom: 5}}>
            <CartesianGrid strokeDasharray="3 3"/>
            <XAxis dataKey="flightNumber"/>
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
