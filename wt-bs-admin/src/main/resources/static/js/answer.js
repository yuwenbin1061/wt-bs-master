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
        url: "/answer/getList", //必选
        queryParams: queryParams, //参数
        responseHandler : function(result) {
            if(result.code != "0000") {
                alert("検索失敗");
            }
            return result.data;
        },
        columns: [
            // [
            //     {
            //         // field: 'descs',
            //         title: 'テキスト',
            //         align: 'left',
            //         // valign: 'middle'
            //     },
            //     {
            //         field:'descs',
            //         colspan :7,
            //     }
            // ],
            // [
                {
                    field: '',
                    checkbox: true
                },
                {
                    field: 'descs',
                    title: 'テキスト',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'answer',
                    title: '回答',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'doingAnswer',
                    title: '未回答',
                    align: 'center',
                    valign: 'middle',
                    cellStyle:  function cellStyle(value, row, index) {
                        return {
                            css : {
                                "color" : "red"
                            }
                        };
                    },

                },
                {
                    field: 'doneAnswer',
                    title: '正しい回答',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'failAnswer',
                    title: '間違った回答',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'score',
                    title: '質問の点数',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'currentScore',
                    title: '得点',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'studentCode',
                    title: '学生番号',
                    align: 'center',
                    valign: 'middle'
                }
            ]

        // ]
    });
}


//查询条件
function queryParams(params) {
    params.problemId = $("#problemId").val();
    return params;
}

// 查询
function find(){
    $("#table").bootstrapTable('selectPage', 1).bootstrapTable("refresh");
}



// 答案添加
function addProblem() {
    $.ajax({
        type : "POST",
        async : false,
        data:$('#answerFrom').serialize(),
        dataType: 'json',
        url : "/answer/save",
        success:function(data){
            if(data.code=='0000'){
                alert("あなたの得点は:" + data.data);
                window.location.href ="/problems/list";
            }else{
                alert(data.msg);
            }
        }
    });
}