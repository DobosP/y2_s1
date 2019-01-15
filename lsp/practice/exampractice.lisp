
; repalce elem from lvl k

(defun replacelvl( L K lvl)
  (cond
    ((NULL L) (list))
    ((and (atom (car L)) (equal K lvl))  (cons 0 (replacelvl (cdr L) K lvl)))
    ((atom (car L))  (cons (car L) (replacelvl (cdr L) K lvl)))
    ((listp (car L)) (cons (replacelvl (car L) K (+ lvl 1)) (replacelvl (cdr L) K lvl)) )
  )
)

(defun replacelvl2(L K lvl)
  (cond
    ((and (atom L) (equal K (- lvl 1)))  0)
    ((atom  L) L)
    ((listp L) (apply 'list (mapcar (lambda (e) (replacelvl2 e K (+ lvl 1)) ) L )))
  )
)

(defun pathtree(tree e)
  (cond

    ((NULL tree) nil)
    ((equal (car tree) e)  (list e))
    (

      (not (equal (pathtree (cadr tree) e) nil))
      (cons (car tree) (pathtree (cadr tree) e))
    )
    (
      (not (equal (pathtree (caddr tree) e) nil))
      (cons (car tree) (pathtree (caddr tree) e))
    )
  )
)



( defun lastnotatom(L)
  (cond
  ((and (null (cdr L)) (listp (car L))) (lastnotatom (car L)))
  ((and (null (cdr L)) (not (Integerp (car L)))) T)
  ((null (cdr L)) nil)
  (T (lastnotatom (cdr L)))
  )
)

(defun solveproblem(L)
  (cond
    ((NULL L) 0)
    ((atom L) 0)
    ((and (listp L) (lastnotatom L)) (+ 1 (apply '+(mapcar 'solveproblem L))))
    (T (apply '+(mapcar 'solveproblem L)))
  )

)

; Sa se scrie de 2 ori elementele din N in N


(defun write2n(L K N)
  (cond
  ((null L) nil)
  ((equal K N) (append (list (car L) (car L)) (write2n (cdr L) 1 N)))
  (T (cons (car L) (write2n (cdr L) (+ 1 K) N)))
  )
)


(defun findmax(L)
  (cond
    ((Integerp L) L)
    ((atom L) -999)
    (T (apply 'max(mapcar 'findmax L)))
  )
)

(defun getlistsmax(L)
  (cond
  ((NULL L) (list))
  ((atom L) (list))
  (
    (and (listp L) (equal (mod (findmax L) 2 ) 0))
    (append  (list L)  (apply  'append(mapcar 'getlistsmax L)))
  )
    (T (apply  'append(mapcar 'getlistsmax L)))
  )
)


(defun replacenode(tree n e)
  (cond
    ((and (atom tree) (equal n tree)) e)
    ((atom tree) tree)
    (
      T
       (mapcar (lambda (el) (replacenode el n e)) tree)
    )
  )
)


(defun linwithint(L)
  (cond
    ((Integerp L) (list L))
    ((atom L) (list))
    (T  (apply 'append(mapcar 'linwithint L)))
  )
)

(defun lastisnonnumeric(L)
  (cond
    ((atom L) 0)
    (
      (and (listp L) (equal 1 (mod (car (reverse(linwithint L))) 2) ) )
      (+ 1 (apply '+(mapcar 'lastisnonnumeric L) ))
    )
    (T (apply '+(mapcar 'lastisnonnumeric L)))
  )
)


(defun maxatodd(L K)
  (cond
  ((and (numberp L) (equal 1 (mod K 2))) L)
  ((atom L) -999)
  (T (apply 'max(mapcar (lambda (e) (maxatodd e (+ 1 K))) L)) )
  )
)

(defun countodd(L lvl)
  (cond
  ((atom L) 0)
  (
    (and (listp L) (equal 0 (mod (findmax L) 2)) (equal 1 (mod lvl 2)))
    (+ 1 (apply '+(mapcar (lambda (e) (countodd e (+ 1 lvl) ) ) L)))
  )
  (T (apply '+(mapcar (lambda (e) (countodd e (+ 1 lvl) ) ) L)) )
  )
)
