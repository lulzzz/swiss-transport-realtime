import React, {Component, Provider} from 'react';
import ReactDOM  from 'react-dom';
import {connect} from 'react-redux';
import styles from '../../css/app.css';
import matStyles from 'materialize-css/bin/materialize.css';

import PositionMap from './PositionMap';
import StationBoardDetails from './StationBoardDetails';
import Timer from './Timer';

class Home extends Component {
  render() {
    let _this = this;

    return (
      <div className={matStyles.row}>
        <div className={matStyles.col + ' ' + matStyles.s8} style={{height:global.window.innerHeight*0.8}}>
          <PositionMap
            store={_this.props.store}
          />
        </div>
        <div className={matStyles.col + ' '+matStyles.s4}>
          <StationBoardDetails store={_this.props.store}/>
        </div>
      </div>
    );
  }
}

export default connect(state => state)(Home);
