import React from "react";
const Category = (props) => {
    return (
        <div className="container mt-3">
            <h2>Categories</h2>
            <table className="table table-hover table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                </tr>
                </thead>
                <tbody>
                {props.categories.map((category) => (
                    <tr>
                        <td>{category}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Category;