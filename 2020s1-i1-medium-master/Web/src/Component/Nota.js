import '../App.css';
import React from 'react';

// import dataclass

function Nota(  ){ 
  
     const {id, title, body, categories, author, comments:[]} = nota;

addComment = nota => {
  let nuevosComentarios;
  nuevosComentarios = [{ comments, ...this.state.comments}];
  }
  this.setState({ comments: nuevosComments});
}

    return( 
      
        <div className="notas">
        <h2>{this.author}</h2>
          <p className={this.body}></p>
          <div className="contenedor-btn-nota">
            <div className="botones-acciones"> Comentar </div>
            <div className="comentarios-lista">
              <ul>
              {comments.map(comment => <comments key={nota.id} comment ={comment} addComment/>
      )}
              </ul>
            </div>
          </div>
        
        </div>
    
);


    }
    

  

export default Nota;

