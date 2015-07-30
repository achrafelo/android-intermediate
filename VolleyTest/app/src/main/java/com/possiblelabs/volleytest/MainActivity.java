package com.possiblelabs.volleytest;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.possiblelabs.volleytest.service.DelPostCallback;
import com.possiblelabs.volleytest.service.Post;
import com.possiblelabs.volleytest.service.PostCallback;
import com.possiblelabs.volleytest.service.PostHelper;
import com.possiblelabs.volleytest.service.PostsCallback;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private String defaultId = "1";
    private BaseAdapter adapter;
    private List<Post> data = new ArrayList<Post>();
    private PostHelper ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvPosts = (ListView) findViewById(R.id.lv_posts);
        adapter = new PostAdapter();
        lvPosts.setAdapter(adapter);
        ph = new PostHelper(this);
    }

    public void doClick(View v) {

        int id = v.getId();
        if (id == R.id.btn_get_posts) {
            ph.listPosts(postsCB);
        } else if (id == R.id.btn_get_post) {
            ph.getPost(defaultId, postCB);
        } else if (id == R.id.btn_add_post) {
            Post post = new Post();
            post.setTitle("Custom Title");
            post.setBody("Custom Body");
            post.setUserId("1");
            ph.addPost(post, postCB);
        } else if (id == R.id.btn_update_post) {
            Post post = new Post();
            post.setTitle("Updated Title");
            post.setBody("Updated Body");
            post.setUserId("1");
            ph.updatePost(defaultId, post, postCB);
        } else if (id == R.id.btn_modify_post) {
            Post post = new Post();
            post.setTitle("Modified Title");
            ph.modifyPost(defaultId, post, postCB);
        } else if (id == R.id.btn_del_post) {
            ph.delPost(defaultId, delPostCB);
        }
    }


    private class PostAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            Post p = data.get(position);
            if (p != null && p.getId() != null && !p.getId().isEmpty())
                return Long.parseLong(data.get(position).getId());
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_2, parent, false);

            Post post = data.get(position);
            TextView tvTitle;
            tvTitle = (TextView) convertView.findViewById(android.R.id.text1);
            tvTitle.setText(post.getTitle());
            TextView tvBody = (TextView) convertView.findViewById(android.R.id.text2);
            tvBody.setText(post.getBody());

            return convertView;
        }
    }

    private PostsCallback postsCB = new PostsCallback() {
        @Override
        public void success(List<Post> posts) {
            data.clear();
            data.addAll(posts);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void failure(VolleyError ve) {
            ve.printStackTrace();
        }
    };

    private PostCallback postCB = new PostCallback() {
        @Override
        public void success(Post post) {
            data.clear();
            data.add(post);
            adapter.notifyDataSetChanged();
            if (post != null)
                defaultId = post.getId();
        }

        @Override
        public void failure(VolleyError ve) {
            ve.printStackTrace();
        }
    };

    private DelPostCallback delPostCB = new DelPostCallback() {
        @Override
        public void success(Post post) {
            Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void failure(VolleyError ve) {
            ve.printStackTrace();
        }
    };


}
