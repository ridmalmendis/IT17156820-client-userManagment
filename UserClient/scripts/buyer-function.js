$(document).ready(function () {
    
    var buyer = {};

    getAllBuyers();
    
})

function getAllBuyers() {
    $.ajax({
        url: "http://localhost:8080/gaget/api/users/buyers",
        method: "GET",
        dataType: "json",
        success: function (data) {
            var tableBody = $('#tblBuyer tbody');
            tableBody.empty();
            $(data).each(function (index, element) {
                tableBody.append('<tr><td>' + element.userId + '</td><td>' + element.fname +'</td><td>' + element.lname +
                    '</td><td>' + element.contact + '</td><td>' + element.username +
                    '</td> <td><button type="button" class="btn btn-warning btn-sm" onclick= "updateBuyer(' +
                    element.userId +
                    ')"> Update</button> | <button type="button" class="btn btn-danger btn-sm" onclick= "deleteBuyer(' +
                    element.userId + ')"> Delete </button></td> </tr>');
            })
        },
        error: function (error) {
            alert(error);
        }
    })
}
//get id value to popup modal
function deleteBuyer(id) {
    var buyer = {};
    
    $.ajax({
        url: 'http://localhost:8080/gaget/api/users/buyer/' + id,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            
            $('#txtUserIdD').val(data.userId);
        },
        error: function (error) {
            alert('error', error);
        }
    });
    $('#buyerDeleteModal').modal('show');
}
//trigger delete request
function makeDELETErequest() {
    
    var buyerId = $('#txtUserIdD').val();
  
    $.ajax({
        url: 'http://localhost:8080/gaget/api/users/buyer/' + buyerId,
        method: 'DELETE',
        success: function () {
            alert('record ' + buyerId + ' has been deleted');
            location.reload();
        },
        error: function (error) {
            alert(error);
        }
    });
    $('#buyerDeleteModal').modal('hide');
}

function updateBuyer(id) {
    var buyer = {};
    
    $.ajax({
        url: 'http://localhost:8080/gaget/api/users/buyer/' + id,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            
            $('#txtFirstNameU').val(data.fname);
            $('#txtLastNameU').val(data.lname);
            $('#txtContactU').val(data.contact);
            $('#txtUsernameU').val(data.username);
            $('#txtUserIdU').val(data.userId);           
        },
        error: function (error) {
            alert('error', error);
        }
    })

    $('#buyerUpdateModal').modal('show');
}

function makePUTrequest(){
    var buyer = {};

    buyer.fname = $('#txtFirstNameU').val();
    buyer.lname = $('#txtLastNameU').val();
    buyer.contact = $('#txtContactU').val();
    buyer.username = $('#txtUsernameU').val();
    buyer.userId = $('#txtUserIdU').val();

    var buyerObj = JSON.stringify(buyer);

    $.ajax({
    url: 'http://localhost:8080/gaget/api/users/buyer/',
    type: 'PUT',
    data: buyerObj,
    contentType: 'application/json; charset=utf-8',
    success: function (result) {
        alert('record  has been updated');
        location.reload();
    }
});
$('#buyerUpdateModal').modal('hide');
}
    
