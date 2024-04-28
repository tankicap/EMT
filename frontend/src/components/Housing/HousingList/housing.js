import React,{Component} from "react";
import HousingTerm from "../HousingTerm/housingterm";
import {Link} from 'react-router-dom';
import ReactPaginate from 'react-paginate'


class Housings extends Component {
    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            size: 5
        }
    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.housings.length / this.state.size);
        const housings=this.getHostingPage(offset, nextPageOffset);
        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Category</th>
                                <th scope={"col"}>Hosts</th>
                                <th scope={"col"}>Number of rooms</th>
                            </tr>
                            </thead>
                            <tbody>
                            {housings}
                            </tbody>
                        </table>
                    </div>
                    <div className="col mb-4">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-dark"} to={"/accommodations/add"}>Add new accommodation</Link>
                            </div>
                        </div>
                    </div>

                </div>
                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"m-2"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}/>

            </div>
        )
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }

    getHostingPage = (offset, nextPageOffset) => {
        console.log(offset, nextPageOffset)
        return this.props.housings.map((term, index) => {
            return (
                <HousingTerm term={term}
                             onDelete={this.props.onDelete}
                             onEdit={this.props.onEdit}
                             onRent={this.props.onRent}/>
            );
        }).filter((item, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }

}

export default Housings;
