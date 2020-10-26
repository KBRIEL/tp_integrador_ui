import React  from 'react';
import '../App.css';
import Header from './Header';
import Nota from './Nota';
import Modal from './Modal';

class Author extends React.Component{
    constructor(props){
        super (props);
        this.state= {
            id: "" ,
           name: "",
            email: "",
            password: "",
            photo: "",
      notas: [],
      showModal: false,
      selectedNota: null,
    };
  }

  removeNota = id => {
    const nuevasNotas = this.state.notas.filter(nota => nota.id !== id);
    this.setState({ notas: nuevasNotas });
  }

  addNota = nota => {
    let nuevasNotas;
    if(nota.id) {
      nuevasNotas = this.state.notas.map(not => {
        if (not.id === nota.id) {
          return { ...nota }
        }
        return not;
      });
    } else {
      nuevasNotas = [{ ...nota, id: this.state.notas.length + 1 }, ...this.state.notas];
    }
    this.setState({ notas: nuevasNotas, selectedNota: null });
  }

  openModal = () => {
    this.setState({ showModal: true });    
  }

  closeModal = () => {
    this.setState({ showModal: false });    
  }

  editNota = nota => {
    this.setState({
      selectedNota: nota,
      showModal: true,
    })
  }

  render() {
    const { notas, showModal, selectedNota } = this.state; 
    return (

      <> 
      <Header />
        <div className="container">
         {notas.map(nota => <Nota key={nota.id} nota={nota} removeNota={this.removeNota} editNota={() => this.editNota(nota)} />)}
        </div>
        <div className="btn-add-nota">
          <div className="icon--add" onClick={this.openModal} />Agregar Nota
        </div>
        {showModal && <Modal closeModal={this.closeModal} addNote={this.addNote} selectedNota={selectedNota} />}
      </>
    );
  }
}
    
export default Author;

