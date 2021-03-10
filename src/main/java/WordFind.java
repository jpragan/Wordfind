
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class WordFind {
    public static void main(String[] args) throws Exception {

        Gson gson = new Gson();
        Word words = new Word();
        String wordlist = "[";
        String result = "";
        String realwords = "";

        // String to be tested for all english word permutations.
        String s = "mouth";

        // Generate permutations
        Permutations.Permutation(s, "");

        Iterator<String> it = Permutations.hash_Set.iterator();
        while (it.hasNext()) {
            wordlist = wordlist + "{\"Word\": \"" + it.next() + "\"},";
        };

        wordlist = StringUtils.chop(wordlist);
        wordlist = wordlist + "]";

        try {
            result = Dictionary.findEnglishWords(wordlist);
        } catch (IOException e) {
            System.out.println("exception when calling post");
            e.printStackTrace();
        }


        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray wordName = jsonObject.getAsJsonObject("body").get("Words").getAsJsonArray();
        for (int i = 0; i < wordName.size(); i++) {
            JsonElement Words = wordName.get(i).getAsJsonObject().get("Word");
            JsonElement isWords = wordName.get(i).getAsJsonObject().get("is_word");
            if (isWords.getAsBoolean()) {
                JsonElement Definitions = wordName.get(i).getAsJsonObject().get("Definition");
                String word = Words.getAsString();
                String definition = Definitions.getAsString();
                word = word.replace("\"", "");
                definition = definition.replace("\"", "");
                realwords = realwords + "\n" + word + " -" + definition + "\n";
            }

        }
        System.out.println("real words in " + s + " - " + realwords);
    }
}


final class Dictionary {

    public static String findEnglishWords(String body) throws Exception {

        String result = "";

        //create new HttpClient Object
        HttpClient httpClient = HttpClientBuilder.create().build();


        //Define a postRequest request
        HttpPost postRequest = new HttpPost("https://xfyqi24h44.execute-api.us-east-2.amazonaws.com/beta/wordlist");

        //Set the request post body
        StringEntity userEntity = new StringEntity(body, ContentType.APPLICATION_JSON);

        //make the POST call
        postRequest.setEntity(userEntity);

        //process the response
        HttpResponse response = httpClient.execute(postRequest);
        Scanner sc = new Scanner(response.getEntity().getContent());
        result = sc.nextLine();

        return result;
    }

}

class Permutations {

    static Set<String> hash_Set = new HashSet<>();

    // Recursive function to generate permutations of the string

    static void Permutation(String str, String ans) {

        // If string is empty
        if (str.length() == 0) {

            // Add the generated permutation to the
            // set in order to avoid duplicates
            hash_Set.add(ans);
            return;
        }

        for (int i = 0; i < str.length(); i++) {

            // start at the ith character of str
            char ch = str.charAt(i);
            if (!ans.equals("")) {
                hash_Set.add(ans);

            }
            // Rest of the string after, excluding
            // the ith character
            String ros = str.substring(0, i)
                    + str.substring(i + 1);
            if (!ros.equals("")) {

                hash_Set.add(ros);
            }

            // Recursive call
            Permutation(ros, ans + ch);
        }
    }
}






