(ns personal-website.core
  (:require
   [reagent.core :as reagent]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Home Page

(defn header-component []
  [:div
   [:h1 "About Me"]])

(defn body-component []
  [:div
   [:p "Things I like include:"
    [:ul
     [:li "Music"]
     [:li "Netflix"]
     [:li "Gaming"]]]])

(defn footer-component []
  [:div
   [:p "We chillin for now"]])

(defn home-page []
  [:div
   [header-component]
   [body-component]
   [footer-component]])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")
    ))

(defn reload []
  (reagent/render [home-page]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload))
