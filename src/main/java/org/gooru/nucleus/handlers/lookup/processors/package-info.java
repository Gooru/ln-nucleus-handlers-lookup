/**
 * Created by ashish on 30/12/15.
 */
package org.gooru.nucleus.handlers.lookup.processors;

/**
 * Here is how the format of messages and/or responses should look like for traversal on message
 * bus.
 * <p>
 * This is what message bus consumer should ideally get from the processor layer
 * <p>
 * { "mb.operation.status": "success", "mb.container" : { "http.status": 200, "http.headers": {
 * "content-type" : "application/json" }, "http.body": { // Either one of below three are used
 * "http.response": { }, "http.error": { }, "http.validation.error": { } } }, "mb.event" : { // This
 * will be used to send out event notification // No bearing on response to gateway } }
 * <p>
 * // The message bus consumer will use "mb.operation.status" key and value // from above packet to
 * set the header on message reply // The message body will comprise of whatever is contained in key
 * named // mb.container // The http.status will be used to set the response status // The
 * http.headers will be used to write http headers // The http.body is a container which can contain
 * of three values based // on mb.operation.status. If the status is success, then http.response //
 * is used. If the status is error, then http.error is used. If the status is // error.validation
 * then http.validation.error key is used.
 * <p>
 * <p>
 * // The packet received by gateway would look like this, with one of the three // values in
 * http.body
 * <p>
 * { "http.status": 200, "http.headers": { "content-type" : "application/json" }, "http.body": { //
 * Either one of below three are used "http.response": { }, "http.error": { },
 * "http.validation.error": { } } }
 */
