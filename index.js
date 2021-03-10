const AWS = require('aws-sdk');
AWS.config.update({ region: 'us-east-2' });
const dynamoDB = new AWS.DynamoDB.DocumentClient();

exports.handler = async function(event, context) {

    let word = "";
    var isword = undefined;
    var length = undefined;
    let response;
    let definition;
    var result = "{\"Words\":[]}";
    var resultobj = JSON.parse(result);

    // checks DyanmoDB dictionary for word
    // if data.items.length > 0 a word exists in the dictionary
    function checkDictionary() {
        return new Promise(resolve => {
            dynamoDB.query(params,
                function(err, data) {
                    if (err) {
                        console.error("Unable to query. Error:");
                    }
                    else {
                        length = data.Items.length;
                        console.log("data items - " + JSON.stringify(data.Items))
                        if (length > 0)
                            definition = data.Items[0]["Definition"]
                        resolve(length)
                    }
                })
        })
    }

    async function asyncCall() {
        const result = await checkDictionary();
        createResponse()
        return result
    }

    for (let i = 0, wlength = event.length; i < wlength; i++) {
        word = JSON.stringify(event[i].Word)
        word = word.replace(/"/g, "");
        word = word.charAt(0).toUpperCase() + word.slice(1)
        var params = {
            TableName: 'Dictionary',
            KeyConditionExpression: 'Word = :hashKey',
            ExpressionAttributeValues: {
                ':hashKey': word
            }
        };

        await asyncCall();

        if (isword){
            resultobj['Words'].push({"Word": word, "is_word": isword, "Definition": definition});
        }else{
            resultobj['Words'].push({"Word": word, "is_word": isword});
        }

    }


    function createResponse() {
        let responseCode = 200;
        if (length > 0) {
            isword = true;
        }
        else {
            isword = false;
        }
        response =
            {
                statusCode: responseCode,
                headers: {
                    "Words Checked": word
                },
                body: resultobj
            };
    }

    createResponse();
    // console.log("response - " + JSON.stringify(obj));
    return response;

};
