
var prefix = '/actuator/metrics/';

var aMetrics = [
    'jvm.memory.max',
    'jvm.memory.committed',
    'jvm.memory.used',
    'jvm.buffer.memory.used',
    'jvm.buffer.count',
    'jvm.threads.daemon',
    'jvm.threads.live',
    'jvm.threads.peak',
    'jvm.classes.loaded',
    'jvm.classes.unloaded',
    'jvm.gc.memory.allocated',
    'jvm.gc.memory.promoted',
    'jvm.gc.max.data.size',
    'jvm.gc.live.data.size',
    'jvm.gc.pause'
];

$(function () {
    f();
    var sDate = '数据获取时间 ' + moment().format('YYYY年MM月DD日 HH时mm分ss秒');
    $("#disDate").html(sDate);
})
function f() {
    for (var j = 0, n = aMetrics.length, url; j < n; ++j) {
        url = prefix + aMetrics[j];
        $.ajax({
            async: false,
            cache: false,
            timeout: 5000,
            type: "GET",
            url: url,
            complete:ajaxComplete.bind(null, aMetrics[j])
        });
    }
}
function ajaxComplete(id, result) {
    var baseUnit = result.responseJSON.baseUnit;
    var val = result.responseJSON.measurements[0].value;
    switch (baseUnit) {
        case "bytes":
            val = Number(val / 1048576).toFixed(3);
            break;
        default:
            break;
    }
    if (id == 'jvm.gc.pause'){
        $("[id='jvm.gc.pause.count']").html(val);
        $("[id='jvm.gc.pause.totalTime']").html(result.responseJSON.measurements[1].value);
    }
    $("[id='"+id+"']").html(val);

}