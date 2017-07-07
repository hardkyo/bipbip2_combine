package com.kitri.factory;

import com.kitri.action.Action;
import com.kitri.gallery.action.*;

public class GalleryActionFactory {

	private static GalleryViewAction galleryViewAction;
	private static GalleryListAction galleryListAction;
	private static GalleryModifyAction galleryModifyAction;
	private static GalleryDeleteAction galleryDeleteAction;
	private static GalleryMvModifyAction galleryMvModifyAction;

	static {

		galleryViewAction = new GalleryViewAction();
		galleryListAction = new GalleryListAction();
		galleryModifyAction = new GalleryModifyAction();
		galleryDeleteAction = new GalleryDeleteAction();
		galleryMvModifyAction = new GalleryMvModifyAction();
	}

	public static GalleryViewAction getGalleryViewAction() {
		return galleryViewAction;
	}

	public static GalleryListAction getGalleryListAction() {
		return galleryListAction;
	}

	public static GalleryModifyAction getGalleryModifyAction() {
		return galleryModifyAction;
	}

	public static GalleryDeleteAction getGalleryDeleteAction() {
		return galleryDeleteAction;
	}
	public static GalleryMvModifyAction galleryMvModifyAction() {
		return galleryMvModifyAction;
	}

}
