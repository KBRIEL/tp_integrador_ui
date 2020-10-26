import React from 'react';
import '../App.css';




class Header extends React.Component {

render() {
    return (
        
            <div className="container-block">
                <div>
                    <header>
                        <img src="../logo.jpg" className="App-logo" alt="logo"/>
                        <div>
                            <ul>
                                <li>{Login}</li>
                                <li>{SignUp}</li>
                            </ul>
                        </div> 
                                <span class="input-group-btn">
                                <div class="input-group">
                                    <input placeholder="Buscar" class="form-control form-text" type="text" size="15" maxlength="128" />
                            
                                <button type="submit" class="btn-search">Buscar<span class="icon glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                </div>
                                </span>
                            
                        
                    </header>
                </div>
                
            </div>
        
    );

}
}
export default Header;