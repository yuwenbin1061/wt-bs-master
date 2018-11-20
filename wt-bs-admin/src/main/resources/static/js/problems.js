var table;

//初始化
function initTable() {

    // #######不用动begin#########
    table = $('#table').bootstrapTable({
        sidePagination: "server", //表格分页的位置,必选
        method: 'post',//必选
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',//编码,必选
        pagination: true, //启动分页
        pageSize: 10, //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [10, 20, 30], //记录数可选列表
        striped: true,
        clickToSelect: true,//点击行即可选中单选/复选框
        formatLoadingMessage: function () {
            return "少々お待ちください...";
        },
        formatNoMatches: function () {  //没有匹配的结果
            return '該当する記録はありません';
        },
        url: "/problems/getList", //必选
        queryParams: queryParams, //参数
        responseHandler : function(result) {
            if(result.code != "0000") {
                alert("検索失敗");
            }
            return result.data;
        },
        columns: [
            {
                field: 'descs',
                title: 'テキスト',
                align: 'center',
                valign: 'middle'
            },
            {
                field: 'isShow',
                title: '公開するか',
                align: 'center',
                valign: 'middle',
                formatter: function (value) {
                    if (value == 1){
                        return "はい";
                    }
                    return "いいえ";
                }
            },
            {
                field: 'id',
                title: '操作',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row) {
                    const id = row['id'];
                    const answer = '<a  href="/answer/answerPage?id=' + id + '" class="am-btn am-btn-default am-btn-xs am-text-secondary"><i class="am-icon-pencil-square-o"></i>答える</a>';
                    const isShow = '<a href="#" onclick="isShowBtn(this,\'' + id + '\')" class="am-btn am-btn-default am-btn-xs am-text-secondary"> <i class="am-icon-pencil-square-o"></i>公開</a> ';
                    const sAnswer = '<a  href="/answer/list?id=' + id + '" class="am-btn am-btn-default am-btn-xs am-text-secondary"><i class="am-icon-pencil-square-o"></i>回答を見る</a>'

                    var identity = $("#identity").val();
                    if(identity == 2){
                        return  sAnswer + "  " + answer;
                    }
                    return isShow + "  " + sAnswer + "  " + answer;
                }
            }
        ]
    });
}


//查询条件
function queryParams(params) {
    params.descs = $("#descs").val();
    params.identity = $("#identity").val();
    params.batchNo = $("#batchNo").val();
    return params;
}

// 查询
function find(){
    $("#table").bootstrapTable('selectPage', 1).bootstrapTable("refresh");
}

// 展示
function isShowBtn(that,id) {
    jQuery.ajax({
        type:"post",
        url: "/problems/isShow" + "?id=" + id,
        success : function(data) {
            console.info(data);
            if (data.code === "0000") {
                alert("この問題の回答は公開されます");
            }else{
                alert(data.result);
            }
        }
    });
}

// 问题添加
function addProblem() {
    $.ajax({
        type : "POST",
        async : false,
        data:$('#problemFrom').serialize(),
        dataType: 'json',
        url : "/problems/save",
        success:function(data){
            if(data.code=='0000'){
                alert("成功");
                window.location.href ="/problems/add?batchNo=" + data.msg+"&paperName="+$('#paper-name').val();
            }else{
                alert("失敗");
            }
        }
    });
}

// 完成试卷
function complete() {
    window.location.href ="/papers/list";
}

