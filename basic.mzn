var 1..5: wa;
var 1..5: nt;
var 1..5: sa;
var 1..5: q;
var 1..5: nsw;
var 1..5: v;
var 1..5: t;

constraint wa == nt;
constraint wa <= sa;
constraint nt != sa;
constraint nt > q;
constraint sa != q;
constraint sa != nsw;
constraint sa != v;
constraint q != nsw;
constraint nsw != v;

solve satisfy;

output ["wa=", show(wa),
       "\t nt=", show(nt),
       "\t sa=", show(sa),
       "\t q=", show(q),
       "\t nsw=", show(nsw),
       "\t v=", show(v),
       "\t t=", show(t),  "\n"];

