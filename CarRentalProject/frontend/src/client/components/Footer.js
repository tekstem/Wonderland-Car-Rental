import React, { Component } from 'react'

export class Footer extends Component {
  render() {
    return (
      <footer className="page-footer font-small blue bg-dark">
<div  style={{textAlign:"center"}}>
          <a href= "#"> Home  ||</a>
          <a href= "/aboutUs">About-us  ||</a>
          <a href= "https://twitter.com/">contact Us</a>
</div>

        <div className="footer-copyright text-center py-3"> 
        <span style={{color: 'white'}}>© 2023 Copyright: </span> 
        <a href="/aboutUs" style={{textDecoration: 'none'}}><span
            style={{color: 'tomato', fontStyle: 'italic'}}>WonderLand
            WONDERLAND CAR RENTAL SYSTEM</span> </a>
        </div>      
      </footer>
    )
  }
}

export default Footer;