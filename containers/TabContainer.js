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

export default class TabContainer extends Component {

  render () {
    return (
      <div>
      <Typography component="div" style={{ padding: 8 * 3 }}>
        {this.props.children}
      </Typography>

       </div>
    );
  }
}

TabContainer.propTypes = {
  children: PropTypes.string.isRequired,
};
