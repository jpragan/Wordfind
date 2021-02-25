const AWS = require('aws-sdk');
AWS.config.update({ region: 'us-east-2' });
const dynamoDB = new AWS.DynamoDB.DocumentClient();

exports.handler = async function(event, context) {

    let word = "";
    var isword = false;
    var length = undefined;
    let responseBody;
    let response;

    //get word from query string
    if (event.queryStringParameters && event.queryStringParameters.word) {
        console.log("Received word: " + event.queryStringParameters.word);
        word = event.queryStringParameters.word;
    }

    // All words in database have capital first letter and wont match if first letter isn't capitalized.
  word = word.charAt(0).toUpperCase() + word.slice(1)

    //set up parameter to send to DynamoDB
    var params = {
        TableName: 'Dictionary',
        KeyConditionExpression: 'Word = :hashKey',
        ExpressionAttributeValues: {
            ':hashKey': word
        }
    };

// call to dynamoDB to check if the word exists or not
    function checkDictionary() {
        return new Promise(resolve => {
            dynamoDB.query(params,
                function(err, data) {
                    if (err) {
                        console.error("Unable to query. Error:");
                    }
                    else {
                        length = data.Items.length; // length is the number of definitions for a word that is returned
                        resolve(length)             // length > 0 indicates that the word exists.
                    }
                })
        })
    }

// these calls needed to be asynchronous due to the dynamoDB call.
    async function asyncCall() {
        const result = await checkDictionary();
        //console.log(result);
        createResponse()
        return result
    }

    await asyncCall();


    //build the response
    function createResponse() {
        let responseCode = 200;
        if (length > 0) {
            isword = true;
        }
        responseBody = {
            is_word: isword,
        };
        response = {
            statusCode: responseCode,
            headers: {
                "Words Checked": word
            },
            body: JSON.stringify(responseBody)
        };
    }

    return response;

};
