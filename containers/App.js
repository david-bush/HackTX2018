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
import TabContainer from './TabContainer.js';
import AirportSentiment from './AirportSentiment.js';
import FlightSentiment from './FlightSentiment.js';
import HomeDepot from './HomeDepot.js';
import Paper from '@material-ui/core/Paper';



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
              <Typography variant="h4" color="inherit">
                HackTX
              </Typography>
            </Toolbar>
          </AppBar>

        <div>
        <AppBar position="static">
          <Tabs value={value} onChange={this.handleChange} centered>
            <Tab label="Airport Sentiment" />
            <Tab label="Flight Sentiment" />

          </Tabs>
        </AppBar>
        </div>
        {value === 0 && <AirportSentiment>{"Item One"}</AirportSentiment>}
        {value === 1 && <FlightSentiment>{"Item Two"}</FlightSentiment>}

      </div>
    );
  }
}
