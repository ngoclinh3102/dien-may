package bean;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/09/03
*/

import model.ReportProduct;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import service.ReportService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "ReportBean", eager = true)
@ViewScoped
public class ReportBean extends BaseBean {
    /* DECLARE */
    private BarChartModel barModel;

    private final List<String> bgColor = new ArrayList<>(Arrays.asList(
            "rgba(255, 99, 132, 0.2)",
            "rgba(255, 159, 64, 0.2)",
            "rgba(255, 205, 86, 0.2)",
            "rgba(75, 192, 192, 0.2)",
            "rgba(54, 162, 235, 0.2)",
            "rgba(153, 102, 255, 0.2)",
            "rgba(201, 203, 207, 0.2)",

            "rgba(255, 99, 132, 0.2)",
            "rgba(255, 159, 64, 0.2)",
            "rgba(255, 205, 86, 0.2)",
            "rgba(75, 192, 192, 0.2)",
            "rgba(54, 162, 235, 0.2)",
            "rgba(153, 102, 255, 0.2)",
            "rgba(201, 203, 207, 0.2)"
    ));

    private final List<String> borderColor = new ArrayList<>(Arrays.asList(
            "rgb(255, 99, 132)",
            "rgb(255, 159, 64)",
            "rgb(255, 205, 86)",
            "rgb(75, 192, 192)",
            "rgb(54, 162, 235)",
            "rgb(153, 102, 255)",
            "rgb(201, 203, 207)",

            "rgb(255, 99, 132)",
            "rgb(255, 159, 64)",
            "rgb(255, 205, 86)",
            "rgb(75, 192, 192)",
            "rgb(54, 162, 235)",
            "rgb(153, 102, 255)",
            "rgb(201, 203, 207)"
    ));

    @PostConstruct
    public void init() {
        createBarModel();
    }

    /* GETTER & SETTER */
    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    /* METHOD */
    public void createBarModel() {
        List<ReportProduct> list = ReportService.top5Product("31 DAY");
        System.out.println(list);

        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();

        barDataSet.setLabel("TOP5 SẢN PHẨM BÁN CHẠY NHẤT");

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (ReportProduct item : list) {
            values.add(item.getTotal());
            labels.add(item.getProductName());
        }
//        values.add(65);
//        values.add(59);
//        values.add(80);
//        values.add(81);
//        values.add(56);
//        values.add(55);
//        values.add(40);
//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//        labels.add("June");
//        labels.add("July");

        barDataSet.setData(values);

        barDataSet.setBackgroundColor(bgColor);
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        data.setLabels(labels);

        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        ticks.setStepSize(1);
        ticks.setMin(0);
        ticks.setMax(list.get(0).getTotal()+1);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

//        Title title = new Title();
//        title.setDisplay(true);
//        title.setText("Thống kê");
//        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }
}
