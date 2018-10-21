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
import AirportSentiment from './AirportSentiment.js';
import FlightSentiment from './FlightSentiment.js';
import TopAirport from './TopAirport.js';
import Paper from '@material-ui/core/Paper';
import TopCategories from './TopCategories.js';
import Demo from './Demo.js';

export default class App extends Component {
  state = {
     value: 0,
   };


   handleChange = (event, value) => {
     this.setState({ value });
   };
  render () {
    const { value } = this.state;

    return (
      <div>

          <AppBar position="static" item xs={24}>
            <Toolbar>
              <img style={{width: '75px', height: '75px'}} src="http://logok.org/wp-content/uploads/2014/02/American-Airlines-Eagle-logo.png"></img>
              <Typography variant="h4" color="inherit">
                Air Thoughts
              </Typography>
            </Toolbar>
          </AppBar>

        <div>
        <AppBar position="static">
          <Tabs value={value} onChange={this.handleChange} centered>
            <Tab label="Airport Sentiment" />
            <Tab label="Flight Sentiment" />
            <Tab label="Top Airport Categories"/>
            <Tab label="Top Flight Categories" />
            <Tab label="Demo"/>
          </Tabs>
        </AppBar>
        </div>
        {value === 0 && <AirportSentiment>{"Item One"}</AirportSentiment>}
        {value === 1 && <FlightSentiment>{"Item Two"}</FlightSentiment>}
        {value === 2 && <TopAirport>{"Item Three"}</TopAirport>}
        {value === 3 && <TopCategories>{"Item Four"}</TopCategories>}
        {value === 4 && <Demo>{"Item Five"}</Demo>}

      </div>
    );
  }
}
