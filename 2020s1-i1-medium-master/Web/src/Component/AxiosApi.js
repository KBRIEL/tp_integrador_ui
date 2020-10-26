import axios,{response} from 'axios';






const register =(email,name,password,photo)=>axios.post(`http://localhost:7000/register`,{name:name,email:email, password:password,photo:photo},{headers:{ Authorization:response.headers.authorization}})
                                                    

const login=(email, password)=>axios.post(`http://localhost:7000/login`, {email:email, password:password},{headers:{ Authorization:response.headers.authorization}});


const getUser=()=>axios.get('http://localhost:7000/user',{headers:{ Authorization:response.headers.authorization}})
                                .then(response=> this.setState({user: response.data}));

const getUserNotes=()=>axios.get('http://localhost:7000/user/notes',{headers:{ Authorization:response.headers.authorization}})
                                .then(response=> this.setState({notes: response.data}));
                                
const search=(text=>axios.get(`http://localhost:7000/search?text=${text}`,{headers:{ Authorization:response.headers.authorization}}))
                                .then(response=> this.setState({notes: response.data}));

const getContent=()=>axios.get('http://localhost:7000/content',{headers:{ Authorization:response.headers.authorization}})
                                .then(response=> this.setState({notes: response.data}));


const getContentId=(id)=>axios.get(`http://localhost:7000/content/${id}`,{headers:{ Authorization:response.headers.authorization}})
                                .then(response=> this.setState({note: response.data}));


const postContentId=(id,aComment)=>axios.post(`http://localhost:7000/content/${id}`,{"body" : aComment},{headers:{ Authorization:response.headers.authorization}})
                                 .then(response=> this.setState({note: response.data}));

const getCategoryName=(name)=>axios.get(`http://localhost:7000/category/${name}`, {headers:{ Authorization:response.headers.authorization}})
                                    .then(response=> this.setState({notes: response.data}));








export default{register, login, getUser, search, getContent, getContentId, postContentId, getCategoryName,  getUserNotes};