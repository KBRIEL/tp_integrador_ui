import React from 'react';
import Nota from './Nota';

class Modal extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      
     id: props.selectedNote ? props.selectedNote.id : undefined,
      title:props.selectedNote ? props.selectedNote.title : '', 
       body: props.selectedNote ? props.selectedNote.body : '',
      
      categories: props.selectedNote ? props.selectedNote.categories : '', 
      author:props.selectedNote ? props.selectedNote.author : '', 
      comments: [], 
    };
   
  }

  updateText = ev => {
    this.setState({body: ev.target.value });
    
  }

  addNote = () => {
    const { addNote, closeModal } = this.props;
    const { body, id } = this.state;
    addNote({id: "",   
      title: "", 
      body: "", 
      categories:[], 
      author: "", 
      comments:[], });
    closeModal();
  }

  render() {
    
    const { body } = this.state;
    const { closeModal } = this.props;

    return (
      <div className="background">
        <div className="modal">
          <div className="modal__header">
            
            <h2 className="header">{Nota}</h2>
                     
            </div>
          </div>
          <div className="modal__content">
            <div>
              <textarea type="text" value={body} onChange={this.updateText} maxLength="300" rows="7" />
            </div>
            <div className="btns">
              <div className="btn-modal" type="submit" onClick={closeModal}>Cancelar</div>
              <div className="btn-modal" type="submit" onClick={this.addNote}>Guardar</div>
            </div>
          </div>
        </div>
     
    )
  }
}

export default Modal;