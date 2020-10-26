import React, { Component } from "react";
import {Link} from "react-router-dom";
import Header from './Header';
// import register from 'AxiosApi'


export default class SignUp extends Component {
    constructor(props){
    super (props);
    this.state={
        user: "",
        pass: "",
        email:"",
        image:"",
        creditCard:"",
        ok:false
      }
    }
    // register(){

    //     register(this.state.email,this.state.name,this.state.password,this.state.image,this.state.creditCard )
    //     this.setState({ok:true})
    // }

    render() {
         return (
            <>
            <Header />
            <form>
                <h3>Sign Up</h3>
                <div className="form-group">
                    <label>Email address</label>
                    <input type="text" className="form-control" placeholder="Email Address" />
                </div>
                <div className="form-group">
                    <label>Name</label>
                    <input type="text" className="form-control" placeholder="Name" />
                </div>
                <div className="form-group">
                    <label>Imagelink</label>
                    <input type="email" className="form-control" placeholder="Enter password" />
                </div>
                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" placeholder="Enter image" />
                </div>
                <div className="form-group">
                    <label>Credit card</label>
                    <input type="password" className="form-control" placeholder="Enter your credir cardd" />
                </div>

                <div className = "container">
                     
                   <Link to ="/Home" className ="btn btn-primary btn-block" onClick={ () => this.register().blind(this)}>Submit</Link>
                 </div>
              
            </form> 
            
           
        );
        </>

         )}

   
}

