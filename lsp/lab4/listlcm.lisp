; L1 11
; a
(defun len(L)
  (cond
  ((NULL L) 0)
  (T (+ (len (cdr L)) 1))
  )
)

(defun mygcd(a b)
  (cond
    ((equal b 0) a)
    (T (mygcd b (mod a b)))
  )
)

(defun mylcm(a b)
  (cond
    (T (/ (* a b) (mygcd a b)))
  )
)

(defun listlcm(L)
  (cond
    ((NULL L) 1)
    ((Integerp (car L)) (mylcm (car L) (listlcm (cdr L))))
    ((listp (car L))  (mylcm (listlcm (car L)) (listlcm (cdr L)) ))
    (T (listlcm (cdr L)))
  )
)


; b

(defun montainb(L D)
  (cond
    ((and (equal (len L) 1) (equal D 1)) T)
    ((and (equal (len L) 1) (equal D 0)) NIL)
    ((> (car L) (cadr L)) (montainb (cdr L) 1))
    ((and (< (car L) (cadr L)) (= D 0)) (montainb (cdr L) 0))
    (T NIL)
  )
)

(defun montain(L)
  (cond
    ((or (< (len L) 3) (> (car L) (cadr L))) NIL)
    (T (montainb L 0))
  )
)

; c

(defun findnr(L)
  (cond
    ((NULl L) NIL)
    ((Integerp (car L)) (car L))
    (T (findnr (cdr L)))
  )
)



(defun findmax(L M)
  (cond
    ((NULl L) M)
    ((and (Integerp (car L)) (> (car L) M)) (findmax (cdr L) (car L)))
    ((listp (car L))  (max (findmax (car L) M) (findmax (cdr L) M)))
    (T (findmax (cdr L) M))
  )
)

(defun removemaxb(L M)
  (cond
  ((NULL l) (list))
  ((equal (car L) M) (removemaxb (cdr L) M))
  ((listp (car L))  (cons (removemaxb (car L) M) (removemaxb (cdr L) M) ))
  (T  (cons (car L) (removemaxb (cdr L) M)) )
  )
)
(defun removemax(L)
  (cond
  (T (removemaxb L (findmax L (findnr L))))
  )
)

(defun findprod(L)
  (cond
  ((NULL L) 1)
  ((and (Integerp (car L)) (equal (mod (car L) 2) 0)) (* (car L) (findprod (cdr L))))
  ((listp (car L)) (* (findprod (cdr L)) (findprod (car L))))
  (T (findprod (cdr L)))
  )
)
