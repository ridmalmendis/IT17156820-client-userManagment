$(document).ready(function () {
    
    var seller = {};

    getAllSellers();
    
})

function getAllSellers() {
    $.ajax({
        url: "http://localhost:8080/gaget/api/users/sellers",
        method: "GET",
        dataType: "json",
        success: function (data) {
            var tableBody = $('#tblSeller tbody');
            tableBody.empty();
            $(data).each(function (index, element) {
                tableBody.append('<tr><td>' + element.userId + '</td><td>' + element
                    .companyName +
                    '</td><td>' + element.contact + '</td><td>' + element.username +
                    '</td> <td><button type="button" class="btn btn-warning btn-sm" onclick= "updateSeller(' +
                    element.userId +
                    ')"> Update</button> | <button type="button" class="btn btn-danger btn-sm" onclick= "deleteSeller(' +
                    element.userId + ')"> Delete </button></td> </tr>');
            })
        },
        error: function (error) {
            alert(error);
        }
    })
}
//get id value to popup modal
function deleteSeller(id) {
    var seller = {};
    
    $.ajax({
        url: 'http://localhost:8080/gaget/api/users/seller/' + id,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            
            $('#txtUserIdD').val(data.userId);
        },
        error: function (error) {
            alert('error', error);
        }
    });
    $('#sellerDeleteModal').modal('show');
}
//trigger delete request
function makeDELETErequest() {
    
    var sellerId = $('#txtUserIdD').val();
  
    $.ajax({
        url: 'http://localhost:8080/gaget/api/users/seller/' + sellerId,
        method: 'DELETE',
        success: function () {
            alert('record ' + sellerId + ' has been deleted');
            location.reload();
        },
        error: function (error) {
            alert(error);
        }
    });
    $('#sellerDeleteModal').modal('hide');
}

function updateSeller(id) {
    var seller = {};
    
    $.ajax({
        url: 'http://localhost:8080/gaget/api/users/seller/' + id,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            
            $('#txtCompanyNameU').val(data.companyName);
            $('#txtContactU').val(data.contact);
            $('#txtUsernameU').val(data.username);
            $('#txtUserIdU').val(data.userId);           
        },
        error: function (error) {
            alert('error', error);
        }
    })

    $('#sellerUpdateModal').modal('show');
}

function makePUTrequest(){
    var seller = {};

    seller.companyName = $('#txtCompanyNameU').val();
    seller.contact = $('#txtContactU').val();
    seller.username = $('#txtUsernameU').val();
    seller.userId = $('#txtUserIdU').val();

    var sellerObj = JSON.stringify(seller);

    $.ajax({
    url: 'http://localhost:8080/gaget/api/users/seller/',
    type: 'PUT',
    data: sellerObj,
    contentType: 'application/json; charset=utf-8',
    success: function (result) {
        alert('record  has been updated');
        location.reload();
    }
});
$('#sellerUpdateModal').modal('hide');
}

function reset() {
    $('#txtCompanyName').val('');
    $('#txtContact').val('');
    $('#txtUsername').val('');
    //$('#txtId').val('');
}
        
