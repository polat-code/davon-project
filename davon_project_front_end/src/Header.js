import React from 'react'
import myImage from './images/code.jpg'

const Header = () => {
    
  
    return (
    <div id="header">
    <div className="container header-container">
        
        <div id="item-left"><img src={myImage} alt='logo' style={styles}/></div>
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
