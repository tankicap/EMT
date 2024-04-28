import React from 'react';
import {Link} from 'react-router-dom';

const HousingTerm = (props) => {
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.host.name}</td>
            <td>{props.term.numRooms}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger m-2"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete</a>
                <Link className={"btn btn-primary ml-3"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/accommodations/edit/${props.term.id}`}>
                    Edit
                </Link>
                {props.term.isRent && props.term.numRooms<=0 ? (
                    <a className='btn btn-dark ms-2 disabled' role="button" aria-disabled="true" disabled>Rented</a>
                ) : (
                    <a title={"Rent"} className="btn btn-danger ms-2"
                       onClick={() => props.onRent(props.term.id)}
                    >
                        Rent
                    </a>
                )}

            </td>
        </tr>
    )
}

export default HousingTerm;