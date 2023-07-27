import React from 'react'
import myImage from './images/code.jpg'

const Header = () => {
    
  
    return (
    <div id="header">
    <div class="container header-container">
        
        <div id="item-left"><img src={myImage} style={styles}/></div>
        <div id="item-right">User Management System</div>
        
    </div>
    
</div>
  )
}

const styles = {
    width:"80px",
    height:"80px"
}

export default Header;
