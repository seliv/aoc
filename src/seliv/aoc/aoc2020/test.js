function f(a, b, c) {}



function z(f, c0) {
    var result = function(a, b) {
        return f(a, b, c0);
    };
    return result;
}



function z1(f, c0...) {
    var result = function(a0...) {
        
        return f(a, b, c0);
    };
    return result;
}
