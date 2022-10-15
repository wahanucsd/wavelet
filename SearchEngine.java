import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String handleRequest(URI url) {
        ArrayList<String> store = new ArrayList<String>();
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
            store.add(parameters[1]);
            }
        }
        if (url.getPath().contains("/search")) {
            ArrayList<String> storesearch = new ArrayList<String>();
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                for(int i = 0; i < store.size(); i++){
                    if(store.get(i).contains(parameters[1])){
                    storesearch.add(store.get(i));
                    }
                }
                    return (storesearch.get(0));
                }
        }
            return "404 Not Found!";
        }
        
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
