
import './App.css';
import React, {Component} from "react";
import { BrowserRouter as Router, Routes, Route,Navigate} from 'react-router-dom';
import Category from "../Categories/categories";
import Header from "../Header/header";
import Housings from "../Housing/HousingList/housing";
import HousingService from "../../repository/housingRepository";
import HousingAdd from "../Housing/HousingAdd/housingAdd";
import HousingEdit from "../Housing/HousingEdit/housingEdit";
class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
        housings:[],
        categories: [],
        hosts:[],
        selectedHousing:{}
    }
  }
  render() {
    return(
      <Router>
          <Header/>
          <main>
              <div className="container">
                  <Routes>
                      <Route path={"/categories"} element={<Category categories={this.state.categories} />} />
                      <Route path={"/accommodations/add"} element={<HousingAdd hosts={this.state.hosts} categories={this.state.categories} onAddHousing={this.addHousing}/>} />
                      <Route path={"/accommodations/edit/:id"} element={<HousingEdit hosts={this.state.hosts} categories={this.state.categories} onEditHousing={this.editHousing} housing={this.state.selectedHousing}/>} />
                      <Route path={"/accommodations"} element={<Housings  housings={this.state.housings} onDelete={this.deleteHousing} onEdit={this.getHousing} onRent={this.rentHousing}/>} />
                      <Route path="*" element={<Navigate to="/accommodations" />} />
                  </Routes>
              </div>
          </main>

      </Router>
  );
  }

  loadCategories = () => {
    HousingService.fetchCategories()
        .then((data) => {
          this.setState({
            categories: data.data
          })
        });
  }
    loadHousing = () => {
        HousingService.fetchHousing()
            .then((data) => {
                this.setState({
                    housings: data.data
                })
            });

    }

    deleteHousing = (id) => {
        HousingService.deleteHousing(id)
            .then(() => {
                this.loadHousing();
            });
    }

    loadHosts = () =>{
        HousingService.fetchHost().then((data) => {
            this.setState({
                hosts: data.data
            })
        });
    }
    addHousing = (name, category, hostId, numRooms) => {
        HousingService.addHousing(name, category, hostId, numRooms)
            .then(() => {
                this.loadHousing();
            });
    }

    getHousing = (id) => {
        HousingService.getHousing(id)
            .then((data) => {
                this.setState({
                    selectedHousing: data.data
                })
            })
    }

    editHousing = (id, name, category, hostId, numRooms) => {
        HousingService.editHousing(id, name, category, hostId, numRooms)
            .then(() => {
                this.loadHousing();
            });
    }

    rentHousing = (id) => {
        HousingService.rentHousing(id)
            .then(() => {
                this.loadHousing();
            });
    }


    componentDidMount() {
    this.loadCategories();
      this.loadHousing();
      this.loadHosts();
  }
}

export default App;
