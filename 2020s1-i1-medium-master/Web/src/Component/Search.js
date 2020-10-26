import React from 'react';
import { } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../App.css';
import Header from './Header';
// import axios from 'axiosApi';




class Search extends React.Component {
    constructor (props) {
    super (props);
    this.state = {
        authorId: [],
        searchResult: [], 
        search : '', 
        getAuthor: [],
        getNote: [],
     }  
    };
    
    
    // fun getNote(noteId: String): Note

    // // @Throw NotFound si `authorId` no existe
    // fun getAuthor(authorId: String): Autor
    
    

     render() {


        return (
            <>
            <Header />
                
                <div className="title-home">
                    {this.state.searchResult}  </div>
                <div className="container p-4">
                     <div class="row">
                         <div>
                            <div className="col-m-4">
                              <div className= "card card-body">
                                  <form>
                                    {this.state.searchResult} 
                                    <input type ="text" className="form-control" onChange={this.setstate.onChangeSearchResult}/>
                                   </form>
                              </div>
                            </div>
                            <div className="col-m-8">
                                <ul className="list-group">
                                     {/* {this.state.authorId.map(a => <li className= "list-group-item list-group-item-action" ></li>)}  */}
                                </ul>
                            </div>
                         </div> 
                      
                        
                    </div>    
                </div>
                              
        );</>  
        )}
    
    
}
               
            
            
export default Search;

//  onChangeSearchResult (e) => setSearchResult(e.target.value);