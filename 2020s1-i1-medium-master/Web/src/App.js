import React  from 'react';
import { BrowserRouter , Route , Switch } from 'react-router-dom';
import './App.css';
import Home from './Component/Home'
import Login from './Component/Login';
import SignUp from './Component/SignUp';
import Search from './Component/Search';
import Autor from './Component/Autor';



class App extends React.Component {

  render() {
     return (
      <>
        <div className="">
        <BrowserRouter>
          <div className="App">
            <div>
               
            <Switch> 
              <Route path="/" component={Home} />
              <Route path="/login" component={Login} />
              <Route path="/register" component={SignUp} />
              <Route path="/search?text=?" component={Search} />
              <Route exact path='/user' component={Autor} /> 
             
              <Route path ="*" render = {() => <h1> NOT FOUND </h1>}/>
            </Switch>
          </div> 
    </div>
     
         </BrowserRouter>  
         
        </div>
      </>
    );
  }
}

export default App;
