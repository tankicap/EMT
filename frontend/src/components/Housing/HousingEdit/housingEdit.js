import React from 'react';
import { useNavigate } from 'react-router-dom';

const HousingEdit=(props)=>{
    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        hostId: 1,
        category: "",
        numRooms: 1
    })
    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.housing.name;
        const hostId = formData.hostId !== 0 ? formData.hostId : props.housing.host.id;
        const category = formData.category !== "" ? formData.category : props.housing.category;
        const numRooms = formData.numRooms !== 0 ? formData.numRooms : props.housing.numRooms;

        props.onEditHousing(props.housing.id, name, category, hostId, numRooms);
        navigate('/accommodations')
    }

    return (
        <div className='container'>
            <div className="row mt-5">
                <div className="col-md-5">
                    <form onSubmit={onFormSubmit}>
                        <div className="form-group">
                            <label htmlFor="name">Accommodation name</label>
                            <input type="text"
                                   className="form-control"
                                   id="name"
                                   name="name"
                                   required
                                   placeholder={props.housing.name}
                                   onChange={handleChange}
                            />
                        </div>
                        <div className="form-group">
                            <label>Category</label>
                            <select name="category" className="form-control" onChange={handleChange}>
                                {props.categories.map((term) => {
                                    if (props.housing.category !== undefined && props.housing.category === term) {
                                        return <option value={props.housing.category}
                                                       selected={props.housing.category === term}>{props.housing.category}</option>
                                    } else {
                                        return <option value={term}>{term}</option>
                                    }
                                })}
                            </select>
                        </div>
                        <div className="form-group">
                            <label>Host</label>
                            <select name="hostId" className="form-control" onChange={handleChange}>
                                {props.hosts.map((term) => {
                                    if (props.housing.host !== undefined &&
                                        props.housing.host.id === term.id)
                                        return <option selected={props.housing.host.id}
                                                       value={term.id}>{term.name}</option>
                                    else return <option value={term.id}>{term.name}</option>

                                })}
                            </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="numRooms">Number of rooms</label>
                            <input type="text"
                                   className="form-control"
                                   id="numRooms"
                                   name="numRooms"
                                   placeholder={props.housing.numRooms}
                                   required
                                   onChange={handleChange}
                            />
                        </div>
                        <button id="submit" type="submit" className="btn btn-primary mt-2">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default HousingEdit;