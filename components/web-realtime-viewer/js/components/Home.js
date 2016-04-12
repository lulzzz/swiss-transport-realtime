import React, {Component} from 'react';
import ReactDOM  from 'react-dom';
import {connect} from 'react-redux';
import styles from '../../css/app.css';

import PositionMap from './PositionMap';
import Timer from './Timer';

class Home extends Component {
  render() {
    let _this = this;

    return (
        <div style={{height:global.window.innerHeight*0.8, width:'80%'}}>
          <PositionMap
            store={_this.props.store}
          />
        </div>
    );
  }
}

export default connect(state => state)(Home);
