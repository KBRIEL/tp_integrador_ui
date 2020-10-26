import React from 'react';
import '../App.css';
import Header from './Header';
import notas from '../Notas.json';


class Home extends React.Component{
    state={UltimasNotas: [notas]             
    }
      
    render(){
        const {UltimasNotas}= this.props;
        
    return(
        <>
        <Header/>
        <div className="container">
          {UltimasNotas.map(nota => (
          <UltimasNotas key={nota.id} nota={nota} />
      ))}
    </div>
    <div className="categorias">

      <ul>
        <li onClick={notas.categoria}>Historia</li>
        <li></li>
        <li></li>
        <li></li>
      </ul>
    </div>
    </>
    );
    
          }
        }

    export default Home;