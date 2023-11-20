package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.documentreader01.R;

import java.io.File;
import java.util.ArrayList;

public class rvPdfAdapter extends RecyclerView.Adapter<rvPdfAdapter.AdpterViewHolder> {
    private final Context context;
    private final ArrayList<String> pdfFiles;
    private final LayoutInflater inflater;

    public rvPdfAdapter(Context context, ArrayList<String> pdfFiles) {
        this.context = context;
        this.pdfFiles = pdfFiles;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdpterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use the pre-initialized LayoutInflater
        View v = inflater.inflate(R.layout.document_linear_layout, parent, false);
        return new AdpterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpterViewHolder holder, int position) {
        String path = pdfFiles.get(position);
        File pdfFile = new File(path);
        String fileName = pdfFile.getName();
        String fileDetail = pdfFile.getAbsolutePath();

        holder.txt_docName.setText(fileName);
        holder.txt_docDetail.setText(fileDetail);
    }

    @Override
    public int getItemCount() {
        return pdfFiles.size();
    }

    static class AdpterViewHolder extends RecyclerView.ViewHolder {
        TextView txt_docName, txt_docDetail;

        public AdpterViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_docName = itemView.findViewById(R.id.txt_docName);
            txt_docDetail = itemView.findViewById(R.id.txt_docDetail);
        }
    }
}
