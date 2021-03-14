/** 
 * app.components.AddBookForm
 * 
 * @author Michael Valdron
 * Created on 2021-03-13
 */

// imports
import React from "react";

/** Type for AddBookForm props member */
type AddBookFormProps = {};
/** Type for AddBookForm state member */
type AddBookFormState = {
    field: {[x: string]: string},
    invalid: {[x: string]: boolean}
};

/**
 * React Component for adding a Book entry to a data source.
 * 
 * @param props No props.
 */
export class AddBookForm extends React.Component<AddBookFormProps, AddBookFormState, any> {
    constructor(props: AddBookFormProps) {
        super(props);
        this.state = {
            field: {
                title: "",
                author: "",
                abstract: "",
                genre: "",
                date: "",
                publisher: ""
            },
            invalid: {
                title: false,
                author: false
            }
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }
    
    /**
     * Validates required fields in form and updates state accordingly.
     * 
     * @returns A boolean of whether required fields are validate.
     */
    validate(): boolean {
        let state = this.state;
        let result = true;

        if (this.state.field.title === "") {
            state.invalid.title = true;
            this.setState(state);
            result = false;
        }
        if (this.state.field.author === "") {
            state.invalid.author = true;
            this.setState(state);
            result = false;
        }
        
        return result;
    }

    /**
     * Event handler for when an on form control is changed by the user.
     * This event is important to allow form editing, capturing of form
     * input, and state changes related to changing the form.
     * 
     * @param e ChangeEvent object for triggering form control.
     */
    onChange(e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) {
        const name = e.target.name;
        const value = e.target.value;
        let state = this.state;
        
        if (name === "title")
            state.invalid.title = false;
        if (name === "author")
            state.invalid.author = false;

        state.field[name] = value;

        this.setState(state);
    }

    /**
     * Event handler for when the submission button
     * is clicked and form is in the process of submitting.
     * This handler sends query request back to the main page
     * to be processed.
     * 
     * @param e FormEvent from submission of form.
     */
    onSubmit(e: React.FormEvent<HTMLInputElement>) {
        if (this.validate()) {
            const fields: [string, string][] = Object.entries(this.state.field);
            let url: string = window.location.href;

            e.preventDefault();

            url = url.substr(0, url.indexOf('?'));
            url += `#/?${fields.map((kv) => `${kv[0]}=${kv[1]}`).join("&")}`;
            url += "&action=add_book";
            window.location.href = url;
        }
    }

    /**
     * Produces HTML form of the AddBookForm Component.
     * 
     * @returns AddBookForm's React Component to be rendered by React / Reagent.
     */
    render() {
        return (
            <form className="uk-form-large" id="add_book">
                <div>
                    <input className={`uk-input${(this.state.invalid.title) ? " uk-form-danger" : ""}`} type="text" name="title" placeholder="Book Title" value={this.state.field.title} onChange={this.onChange} required />
                </div>
                <div>
                    <div className="uk-inline" style={{width: "100%"}}>
                        <span className="uk-form-icon" uk-icon="icon: user"></span><input className={`uk-input${(this.state.invalid.author) ? " uk-form-danger" : ""}`} type="text" name="author" placeholder="Book Author" value={this.state.field.author} onChange={this.onChange} required />
                    </div>
                </div>
                <div>
                    <textarea className="uk-textarea" name="abstract" placeholder="Abstract" form="add_book" value={this.state.field.abstract} onChange={this.onChange} />
                </div>
                <div>
                    <input className="uk-input" type="text" name="genre" placeholder="Book Genre" value={this.state.field.genre} onChange={this.onChange} />
                </div>
                <div>
                    <input className="uk-input" type="date" name="date" placeholder="Publish Date of Book" value={this.state.field.date} onChange={this.onChange} />
                </div>
                <div>
                    <input className="uk-input" type="text" name="publisher" placeholder="Book Publisher" value={this.state.field.publisher} onChange={this.onChange} />
                </div>
                <div>
                    <div className="uk-inline">
                        <input className="uk-button uk-button-primary" type="submit" value="Add Book" onClick={this.onSubmit} /><a className="uk-button uk-button-danger" href="/">Cancel</a>
                    </div>
                </div>
            </form>
        );
    }
}