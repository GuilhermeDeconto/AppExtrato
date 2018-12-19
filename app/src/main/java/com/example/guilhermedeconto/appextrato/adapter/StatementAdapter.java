package com.example.guilhermedeconto.appextrato.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.guilhermedeconto.appextrato.R;
import com.example.guilhermedeconto.appextrato.model.Statement;

import java.text.SimpleDateFormat;
import java.util.List;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ViewHolder> {
    private Context context;
    private List<Statement> statement;
    private OnItemClickListner listener;

    public StatementAdapter(Context context, List<Statement> statement) {
        this.context = context;
        this.statement = statement;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder v = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_statementrow, viewGroup, false));
        v.findViewById();
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setValues(statement.get(i));
    }

    @Override
    public int getItemCount() {
        return statement.size();
    }

    public void setListener(OnItemClickListner listener) {
        this.listener = listener;
    }

    public interface OnItemClickListner {
        void onItemClick(Statement statement);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RadioButton rbSelected;
        private TextView tvInstallment;
        private TextView tvAccountNumber;
        private TextView tvData;
        private TextView tvTotalValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        //Procura as views pela id
        public void findViewById() {
            tvInstallment = itemView.findViewById(R.id.tvinstallment);
            tvAccountNumber = itemView.findViewById(R.id.tvAccountNumber);
            tvData = itemView.findViewById(R.id.tvDate);
            tvTotalValue = itemView.findViewById(R.id.tvTotalValue);
            rbSelected = itemView.findViewById(R.id.rbSelected);

        }

        //Seleciona o extrato
        public void setValues(Statement statement) {
            itemView.setOnClickListener(v -> {
                if (rbSelected.isChecked()) {
                    rbSelected.setChecked(false);
                } else {
                    rbSelected.setChecked(true);
                }
                listener.onItemClick(statement);
            });
            tvInstallment.setText(statement.getInstallment());
            tvAccountNumber.setText(statement.getCarnet());
            tvTotalValue.setText(statement.getValue());
            //formata a data a ser exibida por exemplo 11/12/2018 -> 11 DEZ
            try {
                SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat output = new SimpleDateFormat("dd MMM");
                tvData.setText(output.format(input.parse(statement.getPastDue())));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
