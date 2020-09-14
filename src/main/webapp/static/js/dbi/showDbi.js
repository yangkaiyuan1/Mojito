
$(function () {

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    $.get('roomSale/loadRoomSale').done(function (data) {
        myChart.setOption({
            title: {
                text: '客房销售记录分析'
            },
            tooltip: {
                trigger: 'axis'
            },
            toolbox: {
                show: true,
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    dataView: {readOnly: false},
                    magicType: {type: ['line', 'bar']},
                    restore: {},
                    saveAsImage: {}
                }
            },
            legend: {
                "data":data.legend,
            },
            xAxis: {
                data: data.xAxis
            },
            yAxis: {},
            series: [{
                "name": "销售记录",
                "type": "bar",
                "data": data.series
            }]
        });
    });

});