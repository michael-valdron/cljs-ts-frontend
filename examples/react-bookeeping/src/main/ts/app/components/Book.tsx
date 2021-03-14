/** 
 * app.components.Book
 * 
 * @author Michael Valdron
 * Created on 2021-03-12
 */

import { Component } from 'react';

type BookProps = {
    title: string, 
    author: string, 
    abstract: string | null | undefined, 
    genre: string | null | undefined, 
    date: Date | null | undefined, 
    publisher: string | null | undefined
};
type BookState = {};

export class Book extends Component<BookProps, BookState, any> {
    constructor(props: BookProps) {
        super(props);
    }

    render() {
        let abstract: string = (this.props.abstract === undefined || this.props.abstract === null) ? "N/A" : this.props.abstract;
        let genre: string = (this.props.genre === undefined || this.props.genre === null) ? "N/A" : this.props.genre;
        let year: string = (this.props.date === undefined || this.props.date === null) ? "N/A" : this.props.date.getFullYear().toString();
        let publisher: string = (this.props.publisher === undefined || this.props.publisher === null) ? "N/A" : this.props.publisher;
        return (
            <tr>
                <td>{this.props.title}</td>
                <td>{this.props.author}</td>
                <td>{abstract}</td>
                <td>{genre}</td>
                <td>{year}</td>
                <td>{publisher}</td>
            </tr>
        );
    }
}
