import { useNavigate } from 'react-router-dom';
import React from 'react';

const HousingAdd=(props)=>{

    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        hostId: 1,
        category: "APARTMENT",
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
        const name = formData.name;
        const hostId = formData.hostId;
        const category = formData.category;
        const numRooms = formData.numRooms;

        props.onAddHousing(name, category, hostId, numRooms);
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
                                   placeholder="Enter Accommodation name"
                                   onChange={handleChange}
                            />
                        </div>
                        <div className="form-group">
                            <label>Category</label>
                            <select name="category" className="form-control" onChange={handleChange}>
                                {props.categories.map((term) =>
                                    <option value={term}>{term}</option>
                                )}
                            </select>
                        </div>
                        <div className="form-group">
                            <label>Host</label>
                            <select name="hostId" className="form-control" onChange={handleChange}>
                                {props.hosts.map((term) =>
                                    <option value={term.id}>{term.name}</option>
                                )}
                            </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="numRooms">Number of rooms</label>
                            <input type="text"
                                   className="form-control"
                                   id="numRooms"
                                   name="numRooms"
                                   placeholder="Number of rooms"
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

export default HousingAdd;