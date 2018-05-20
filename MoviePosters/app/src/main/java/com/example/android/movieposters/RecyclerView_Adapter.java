package com.example.android.movieposters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**Reference:
 * https://futurestud.io/tutorials/retrofit-2-how-to-add-query-parameters-to-every-request
 * **/

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder> {
   /* String[] values;
    Context context1;
*/
    private List<MovieResult> mMovieList;
    private LayoutInflater mInflater;
    private Context mContext;

    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";
    private final static String API_KEY = "f3221eeb46c806eb9a7fd5656581c50b";

    //This constructor is responsible for passing values to the list
    public RecyclerView_Adapter(Context context) { //, String[] values2
   /*     values = values2;
        context1 = context2;*/
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMovieList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movie_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieResult movie = mMovieList.get(position);
        // This is how to use Picasso to load images from the internet.
        Picasso.with(mContext)
                .load(TMDB_IMAGE_PATH + movie.getPoster_path())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }


    @Override
    public int getItemCount() {
        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    public void setMovieList(List<MovieResult> movieList)
    {
        this.mMovieList.clear();
        this.mMovieList.addAll(movieList);
        // The adapter needs to know that the data has changed. If we don't call this, app will crash.
        notifyDataSetChanged();
    }

    public static class LoggingInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {

            HttpUrl url = chain.request().url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build();

            Request request = chain.request().newBuilder().url(url).build();

            long t1 = System.nanoTime();
            String requestLog = String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers());

  /*          if(request.method().compareToIgnoreCase("post")==0){
                requestLog ="\n" + requestLog + "\n" + bodyToString(request);
            }
  */          Log.d("TAG","request" + "\n" + requestLog);

            okhttp3.Response response = chain.proceed(request);
            long t2 = System.nanoTime();

            String responseLog = String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers());

            String bodyString = response.body().string();

            Log.d("TAG","response" + "\n" + responseLog + "\n" + bodyString);

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString))
                    .build();

        }
    }


    //This is responsible for holding the views
    /*public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textViewName);
        }
    }*/
    /*@Override
    public RecyclerView_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout,parent,false);
        ViewHolder viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(RecyclerView_Adapter.ViewHolder holder, int position) {

        holder.textView.setText(values[position]);

        holder.textView.setBackgroundColor(Color.CYAN);

        holder.textView.setTextColor(Color.BLUE);
    }

    @Override
    public int getItemCount() {
        return values.length;
    }
    */


}
