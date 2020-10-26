import React , {Component, createRef} from 'react';
import '../App.css';

class Comment (val body: String){
   
    
    
    inputText = createRef()
    

    componentDidMount() { 
        this.handleFocus()
    }

    handleFocus = () => {
    this.inputText.current.focus()
    }   

render(){
    reeturn(
        <>
        <input type="text" ref={this.inputText} /> 
        <button className="btn-comentario" onClick={this.handleFocus}>Agregar Comentario</button>

        </>
    );
}
}


export default DraftComment;
