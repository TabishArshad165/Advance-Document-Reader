package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.documentreader01.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class rvPdfAdapter extends RecyclerView.Adapter<rvPdfAdapter.ViewHolder> {

    Activity context;
    List<File> pdfList;

    public rvPdfAdapter(Activity context, List<File> pdfList){
        this.context = context;
        this.pdfList = pdfList;
    }


    @NonNull
    @Override
    public rvPdfAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pdf_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rvPdfAdapter.ViewHolder holder, int position) {
        File file = pdfList.get(position);
        holder.txt_pdfName.setText(file.getName());
        holder.txt_pdfDetails.setText(file.getPath());

        holder.img_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "img_star is pressed", Toast.LENGTH_LONG).show();
            }
        });

        holder.img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "img_more is pressed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pdfList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_pdfName;
        TextView txt_pdfDetails;
        ImageView img_star, img_more;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_pdfName = itemView.findViewById(R.id.txt_pdfName);
            txt_pdfDetails = itemView.findViewById(R.id.txt_pdfDetails);
            img_star = itemView.findViewById(R.id.img_star);
            img_more = itemView.findViewById(R.id.img_more);
        }
    }
}




